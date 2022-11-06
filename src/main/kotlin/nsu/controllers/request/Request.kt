package nsu.controllers.request

interface Request {
    fun bind()
    fun validate()
}