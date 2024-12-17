class Crypto() = {
    mod func hash(data) = {
        crypto = reflect 'com.slavlend.Libraries.crypto'
        @back(@crypto.hash(data))
    }
    mod func encrypt(data, secret) = {
        crypto = reflect 'com.slavlend.Libraries.crypto'
        @back(@crypto.encrypt(data, secret))
    }
    mod func decrypt(data, secret) = {
        crypto = reflect 'com.slavlend.Libraries.crypto'
        @back(@crypto.decrypt(data, secret))
    }
}