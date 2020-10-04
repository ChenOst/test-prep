var analysisByServer;
var userTest;
var fullTest;

function init() {
    analysisByServer = JSON.parse(localStorage.analysisByServer);
    userTest = JSON.parse(localStorage.userTest);
    fullTest = JSON.parse(localStorage.fullTest);
    showScore();
    showTestConclusion();
    showAnotherTestLink();
}

function showScore() {
    let score = analysisByServer.testScore;
    document.getElementById("placeholder").innerHTML += '<section class="containerAnalysis">' +
        '      <form class="my-form">' +
        '        <h1>Final Score: </h1>' + score + "/100" +
        '        <div class="form-check">' +
        '</div>' +
        '</form>' +
        '</section>';
}

function showAnotherTestLink() {
    document.getElementById("placeholder").innerHTML += '<section class="containerAnalysis">' +
        '      <form class="my-form">' +
        '        <center><a href="/index.html"><h1>Start Another Test</h1></a></center>' +
        '        <div class="form-check">' +
        '</div>' +
        '</form>' +
        '</section>';
}

function showTestConclusion() {
    let htmlStr = "";
    for (var i = 0; i < fullTest.length; i++) {
        currentItem = fullTest[i];
        userAnswer = userTest[i].userAnswer;
        let answerStr = "";
        let rightAnswer;
        for (let ans in currentItem.answers) {
            if (currentItem.answers[ans]) { // if its the right answer, save it in rightAnswer variable
                rightAnswer = ans;
            }
            answerStr += `<input type="radio" class="form-check-input" id="answer${i}id" name="groupOfMaterialRadios"`
                + (ans === userAnswer ? " checked " : " ") + "disabled />";
            answerStr += `<label class="form-check-label" for="answer${i}id">` + ans + '</label>';
        }
        htmlStr += '<section class="containerAnalysis">' +
            '      <form class="my-form" style="background:' + (rightAnswer === userAnswer ? '#32CD32;"' : '#FA8072;"') + '>' +
            '        <p style="font-size: 25px;">' + currentItem.description + '</p>' +
            '        <div class="form-check">' +
            answerStr;
        htmlStr += '</div>' +
            '</form>' +
            '</section>';
    }
    document.getElementById("placeholder").innerHTML += htmlStr;
}
