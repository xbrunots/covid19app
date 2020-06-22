package br.com.brunobrito.covidhack.feature.home.shared

import CasesCovid19Data

var issuesItemMock = CasesCovid19Data(
    "https:/.github.com/repos/JetBrains/kotlin/issues/2869",
    "JTK-1928 Testando PR",
    User(
        "brunoBrito",
        "https://avatars1.githubusercontent.com/u/1121855?v=4",
        "https://github.com/t-kameyama"
    )
    ,
    listOf(
        Labels(
            "https://api.github.com/repos/JetBrains/kotlin/labels/Inspections",
            "Inspections", "c14957", "Intentions and Inspection"
        )
    ),
    "open",
    "2019-12-08T06:34:51Z"
)

var issuesItemListMock = listOf(issuesItemMock, issuesItemMock, issuesItemMock, issuesItemMock)