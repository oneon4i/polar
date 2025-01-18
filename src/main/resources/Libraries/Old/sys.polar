class Sys() = {
    mod reflected = reflect 'com.slavlend.Libraries.sys'
    mod func exit(status) = {
        Sys.reflected.exit(status)
    }
    mod func os() = {
        back(Sys.reflected.getOs())
    }
    mod func get_env(name) = {
        back(Sys.reflected.getEnv(name))
    }
    mod func get_cwd() = {
        back(Sys.reflected.getCwd())
    }
}