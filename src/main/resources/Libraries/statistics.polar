use 'lib.array'

class Statistics() = {
    mod reflected = reflect 'com.slavlend.Libraries.statistics'
    mod func mean(arr) = {
        back(Statistics.reflected.mean(arr))
    }
    mod func median(arr) = {
        back(Statistics.reflected.median(arr))
    }
    mod func mode(arr) = {
        back(Statistics.reflected.mode(arr))
    }
    mod func hmean(arr) = {
        back(Statistics.reflected.hmean(arr))
    }
}