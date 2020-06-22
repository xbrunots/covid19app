# Kogit | Kotlin Issues Viewer
by Bruno Brito
_____________________
[![Kogit](http://img.youtube.com/vi/AtjHJvMiDL8/0.jpg)](http://www.youtube.com/watch?v=AtjHJvMiDL8 "Kogit")


# Prova de Android
## Objetivo

Você deve desenvolver uma aplicação Android Nativa em Kotlin que consuma a API
do Github Issues ( https://developer.github.com/v3/issues/ ), usando como base o repositório
do kotlin ( https://github.com/JetBrains/kotlin ).

## O aplicativo deve conter

* Uma tela com a lista de issues do repositório
( https://api.github.com/repos/JetBrains/kotlin/issues ). Cada item da lista deve
conter os seguintes elementos:
    * Título do issue
    * Estado do issue (ABERTO, FECHADO)
* Uma tela mostrando os detalhes do issue que foi selecionado na lista, que deve
conter os seguintes elementos:
    * Título do issue
    * Texto de Descrição do issue
    * Avatar do usuário que criou a issue
    * Data de criação
    * Um botão que abre o browser com o link issue do site do github

## Requisitos obrigatórios

* Kotlin
* RxJava 2
* Koin (preferencialmente) ou Dagger2
* Testes Unitários (de preferência com JUnit / MockK)
* OkHttp3 (preferencialmente) ou Retrofit 2

## Requisitos opcionais

* MVVM
* AndroidX
* Coroutines
* LiveData / Navigation / Action Components

## Pontos de avaliação

* Como você organiza seus arquivos, métodos, nomeia variáveis, lida com o seu
código como um todo são pontos observados.
* Seja cuidadoso, utilize boas práticas e padrões.
* Siga o guideline do Material Design, bem como respeite as boas práticas do
Kotlin. Codifique como você gostaria de trabalhar.
