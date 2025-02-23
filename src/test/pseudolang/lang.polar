use 'lib.system'
use 'lib.files'
use 'lib.strings'

class TokenType() {
    mod LPAREN = 0
    mod RPAREN = 1
    mod LBRACE = 2
    mod RBRACE = 3
    mod IDENTIFIER = 5
    mod NUMBER = 6
    mod COMMA = 7
    mod DOT = 8
    mod SEMICOLON = 9
    mod PLUS = 10
    mod SUB = 11
    mod MUL = 12
    mod DIV = 13
    mod STRING = 14
    mod ASSIGN = 15
}

class Token(type, value) {}

class Scanner(path) {
    func init() {
        this.source = Files.read_text(this.path)
        this.current = 0
        this.tokens = []
    }

    func tokenize() {
        while (this.isAtEnd() == false) {
            char = this.advance()
            match (char.toString()) {
                case ('(') {
                    this.add_token(TokenType.LPAREN, '(')
                } case (')') {
                    this.add_token(TokenType.RPAREN, ')')
                } case ('}') {
                    this.add_token(TokenType.RBRACE, '}')
                } case ('{') {
                    this.add_token(TokenType.LBRACE, '{')
                } case (',') {
                    this.add_token(TokenType.COMMA, ',')
                } case ('.') {
                    this.add_token(TokenType.DOT, '.')
                } case ('-') {
                    this.add_token(TokenType.SUB, '-')
                } case ('+') {
                    this.add_token(TokenType.PLUS, '+')
                } case (';') {
                    this.add_token(TokenType.SEMICOLON, ';')
                } case ('*') {
                    this.add_token(TokenType.MUL, '*')
                } case ('/') {
                    this.add_token(TokenType.MUL, '/')
                } case ('"') {
                    this.add_token(TokenType.STRING, this.tokenizeString())
                } case ('=') {
                    this.add_token(TokenType.ASSIGN, '=')
                }
                default {
                    if (Strings.is_letter(char)) {
                        this.add_token(TokenType.IDENTIFIER, this.tokenizeKeyword(char))
                    } elif (Strings.is_number(char)) {
                        this.add_token(TokenType.NUMBER, this.tokenizeNumber(char))
                    } else {
                        next
                    }
                }
            }
        }
    }

    func tokenizeString() {
        text = ''
        while (this.matches('"') == false) {
            text = text + this.advance().toString()
        }
        this.advance()
        return text
    }

    func tokenizeKeyword(from) {
        text = from.toString()
        while (Strings.is_letter(this.peek()) or this.peek() == '_') {
            text = text + this.advance().toString()
        }
        return text
    }

    func tokenizeNumber(from) {
        text = from.toString()
        while (Strings.is_number(this.peek())) {
            text = text + this.advance().toString()
        }
        return text
    }

    func isAtEnd() {
        return this.current >= this.source.length().floatValue()
    }

    func advance() {
        if (this.isAtEnd()) {return '$'}
        char = this.source.charAt(this.current.intValue())
        this.current += 1
        return char
    }

    func add_token(type, value) {
        this.tokens.add(
            new Token(type, value)
        )
    }

    func peek() {
        if (this.isAtEnd()) {
            return '$'
        }
        v = this.source.charAt(this.current.intValue())
        return v
    }

    func matches(expected) {
        if (this.isAtEnd()) { return false }
        if (this.source.charAt(this.current.intValue()).toString() != expected.toString()) { return false }

        this.current += 1
        return true
    }

    func print() {
        put('-- tokens')
        each (token, this.tokens) {
            put('{' + string(token.type) + ', ' + token.value + '},')
        }
        put('\n-- end')
    }
}

lexer = new Scanner('E:/polar-lang/icevmcompiler/src/test/pseudolang/test.pseudo')
lexer.tokenize()
lexer.print()