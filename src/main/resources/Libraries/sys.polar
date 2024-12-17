class Sys() = {
    mod func exit(status) = {
        sys = reflect 'com.slavlend.Libraries.sys'
        @sys.exit(status)
    }
    mod func os() = {
        sys = reflect 'com.slavlend.Libraries.sys'
        @back(@sys.getOs())
    }
    mod func get_env(name) = {
        sys = reflect 'com.slavlend.Libraries.sys'
        @back(@sys.getEnv(name))
    }
    mod func get_cwd() = {
        sys = reflect 'com.slavlend.Libraries.sys'
        @back(@sys.getCwd())
    }
}