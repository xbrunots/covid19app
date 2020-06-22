package br.com.brunobrito.covidhack.feature.home

import br.com.brunobrito.covidhack.feature.home.domain.HomeInteractor
import br.com.brunobrito.covidhack.feature.home.presentation.HomeActivity
import br.com.brunobrito.covidhack.feature.home.presentation.HomePresenter
import br.com.brunobrito.covidhack.feature.home.shared.issuesItemListMock
import br.com.brunobrito.covidhack.platform.di.KogitModule
import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class HomePresenterTest : KoinTest {

    private lateinit var activity: HomeActivity
    private val interactor: HomeInteractor by inject()
    private val presenter: HomePresenter by inject { parametersOf(activity) }
    private var testScheduler = TestScheduler()


    @Before
    fun setUp() {
        activity = Mockito.spy(HomeActivity())
        startKoin {
            androidContext(activity)
            modules(
                KogitModule.getModules()
            )
        }
        MockitoAnnotations.initMocks(this)
    }

    @Test //teste batendo na api
    fun user_return_test() {
        interactor.getCasesInBrasil("all")
            .test()
            .assertSubscribed()
            .assertNoTimeout()
            .assertComplete() //check complete request
            .assertValue {
                //check states
                it.map { itIssue ->
                    itIssue.state == "open" || itIssue.state == "closed" || itIssue.state == "all"
                }.find { it } ?: false

            }.assertValue { itIssue ->
                //check is github urls
                itIssue.map { itMap ->
                    itMap.html_url.contains("://github.com") || itMap.html_url.contains("://www.github.com")
                }.find { it } ?: false
            }
            .assertNoErrors() //check errors
        testScheduler.triggerActions()
    }

    @Test //teste baseado no mock
    fun user_mock_test() {
        Flowable.just(issuesItemListMock)
            .test()
            .assertSubscribed()
            .assertNoTimeout()
            .assertComplete() //check complete request
            .assertValue { itIssue ->
                //check is github urls
                itIssue.map { itMap ->
                    itMap.html_url.contains("://github.com") || itMap.html_url.contains("://www.github.com")
                }.find { it } ?: false
            }
            .assertNoErrors() //check errors
        testScheduler.triggerActions()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.detachView()
    }
}