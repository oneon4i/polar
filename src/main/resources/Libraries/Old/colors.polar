use 'lib.strings'
use 'lib.map'
use 'lib.polar'
use 'lib.array'

class ColorsLib() = {
  mod color_map = {
    '{red}': '[31m',
    '{white}': '[37m',
    '{blue}': '[34m',
    '{green}': '[32m',
    '{yellow}': '[33m',
    '{reset}': '[0m'
  }

  mod func color(text) = {
     back(ColorsLib.replace_colors(text))
  }

  mod func replace_colors(text) = {
    new_text = text
    each (key, ColorsLib.color_map.keys()) {
      new_text = Strings.replace(new_text, key, ColorsLib.color_map.get(key))
    }
    back(new_text)
  }

  mod func put_color(key, value) = {
    if (Polar.name(key) == 'string') {
      ColorsLib.color_map.add('{' + key + '}', value)
    }
    else {
      put('Invalid color (' + key + ': ' + value + ')')
    }
  }
}