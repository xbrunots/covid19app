package br.com.brunobrito.covidhack.platform.base

interface BaseMvpPresenter {
    fun detachView()
    fun attachView(pais : String)
}
