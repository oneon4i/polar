class Event() {
    func init() {
        this.subscribers = []
    }

    func invoke(args) {
        each(subscriber, this.subscribers) {
            subscriber(args)
        }
    }

    func subscribe(subscriber) {
        this.subscribers.add(subscriber)
    }

    func unsubscribe(subscriber) {
        this.subscribers.del(subscriber)
    }
}