# Библиотеки #
use 'lib.console'
use 'lib.str'
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
    n = len(row)
    result = []
    for (y = 0, y < n) {
        result.add(' ')
        y += 1
    }
    for (j = 0, j < n) {
        new_index = (j + i) % n
        result.set(new_index, Str.at(row, j))
        j += 1
    }
    back(result.stringify())
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
for (ii = 0, ii < 100) {
    put('frame: ' + ii)
    Tasks.exec(animate, [ii])
    ii += 1
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