package ru.spcm.apps.dictionary.model.data

/**
 * Полученные удаленные данные с сообщением об ошибке и текущим статусом
 */
class Resource<out T> private constructor(val status: Status, val data: T?, val message: String?, val code: String? = null) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> error(msg: String?, code: String?): Resource<T> {
            return Resource(Status.ERROR, null, msg, code)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }
}