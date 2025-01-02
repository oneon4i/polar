use 'lib.str'
use 'lib.map'
use 'lib.polar'
use 'lib.array'

class ColorsLib() = {
  mod color_map = {
    '{red}': '\u001B[31m',
    '{white}': '\u001B[37m',
    '{blue}': '\u001B[34m',
    '{green}': '\u001B[32m',
    '{yellow}': '\u001B[33m',
    '{reset}': '\u001B[0m'
  }

  mod func color(text) = {
     back(ColorsLib.replace_colors(text))
  }

  mod func replace_colors(text) = {
    new_text = text
    each (key, ColorsLib.color_map.keys()) {
      new_text = Str.replace(new_text, key, ColorsLib.color_map.get(key))
    }
    back(new_text)
  }

  mod func put_color(key, value) = {
    if (Polar.name(key) == 'string') {
      ColorsLib.color_map.add('{' + key '}', value)
    }
    else {
      put('Invalid color (' + key + ': ' + value + ')')
    }
  }
}