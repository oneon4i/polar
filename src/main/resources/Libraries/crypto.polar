class Crypto() = {
    mod reflected = reflect 'com.slavlend.Libraries.crypto'
    mod func hash(data) = {
        @back(@Crypto.reflected.hash(data))
    }
    mod func encrypt(data, secret) = {
        @back(@Crypto.reflected.encrypt(data, secret))
    }
    mod func decrypt(data, secret) = {
        @back(@Crypto.reflected.decrypt(data, secret))
    }
}