# Библиотеки #
use 'lib.console'
use 'lib.strings'
use 'lib.tasks'

# Основной шаблон кадра #
base_frame = [
     '...................................................................................................',
     '...................................................................................................',
     '...................................................................................................',
     '...................................................................................................',
     '...................................................................................................',
     '...................................................................................................',
     '.......................................................#...........................................',
     '...........%................................%#**+++===*%.....%+%...................................',
     '..........................................%##***++++===--:-#........................#+.............',
     '........................................%%##***++===--:::::::%......................%%.............',
     '.......................................%##**+++==-----:::::::.-++++++++++++**#%....................',
     '......................................%%##**+++===----:::::..::+.%%#**#**++++*=+++.................',
     '.....................................%%%##**+++===---::::::::...%......#****+=**+++%...............',
     '.....................................%%%##***++=====----:::.....*......%*#**+++#=++#...............',
     '.........................##..........%%%%##**+++===---:::::.:...+.....#*#**+=#+=++*................',
     '......................#++*=***%......%%%%#**+++====---::::::..::#..%***#++=%=+++*..................',
     '....................*+=#=+**#*##%......%%%%##**++===-------::--==-*#***+=+#==*++...................',
     '...................++=#+++*#*##%#**++++++++++++=----------::----+++++++*++=**......................',
     '..................%+++=%=++********###%#****++======+====---::+*=++++#.............................',
     '........#:%.......#++++=+#*+=+++++******++++=====------::::=++*%...................................',
     '......................%*++++++===++*****+++=------------=+%........................%...............',
     '.............................%+#%#########%%%...%%####%............................................',
     '...................................................................................................',
     '...................................................................................................',
     '...................................................................................................',
     '...................................................................................................',
     '...................................................................................................',
     '...................................................................................................',
     '...................................................................................................',
     '...................................................................................................'
]

# Создаем анимацию #
frames = []

# Поворот строчки #
func rotate_row(row, i) = {
    length = len(row)
    result = []
    for (y = 0, y < length) {
        result.add(' ')
        y += 1
    }
    for (j = 0, j < length) {
        new_index = (j + i) % length
        result.set(new_index, Strings.at(row, j))
        j += 1
    }
    return(result.stringify())
}

func animate(args) = {
    i = args.get(0)
    frame_rot = []
    each(row, base_frame) {
        frame_rot.add(rotate_row(row, i))
    }
    frame_rot = {'frame': frame_rot, 'id': i}
    frames.add(frame_rot)
}

# Процесс анимирования #
for (n = 0, n < 100) {
    put('frame: ' + n)
    Tasks.exec(animate, [n])
    n += 1
}

# Вывод анимации в консоли #
while (true) {
    while (frames.size() < 100) {
    }
    # Сортируем #
    put(frames.stringify())
    new_frames = []
    for (i = 0, i < 100) {
        each(_frame, frames) {
            if (_frame.get('id') == i) {
                new_frames.add(_frame.get('frame'))
            }
        }

        i += 1
    }
    each(frame, new_frames) {
        for (i = 0, i < frame.size()+1) {
            put('')
            i += 1
        }
        each (row, frame) {
            put(row)  # Печать текущего кадра #
        }
        sleep(100)  # Задержка между кадрами #
    }
}