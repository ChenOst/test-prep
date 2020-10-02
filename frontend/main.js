var questionDataArr;
var index = 0;
var maxIndex;
var testAnalysis = [];

fetch("http://127.0.0.1:8080/random/").then(function (response) {
  if (response.ok) {
    return response.json();
  }
}).then(function (responseJSON) {
  questionDataArr = responseJSON;
  maxIndex = questionDataArr.length;
  renderNextQuestion();
}).catch(function (err) {
  console.log(err);
});

function handleSubmit() {
  event.preventDefault(); // prevents refresh of page


  idOfSelectedAnswerLabel = document.querySelector('input[name="groupOfMaterialRadios"]:checked').id;
  selectedAnswer = document.querySelector(`label[for="${idOfSelectedAnswerLabel}"]`).innerText;
  testAnalysis.push({
    idInDB: questionDataArr[index].id,
    userAnswer: selectedAnswer
  }); // TODO: RE THINK THIS
  console.log(testAnalysis);
  console.log("\n")
  index++;

  if (index === maxIndex) {
    analyzeTest(); // last 'submit' click replace with analyzeTest() when you start working on it
  }

  clearRadioButton();
  renderNextQuestion();
}


function renderNextQuestion() {
  // The question 
  const description = questionDataArr[index].description;
  const question = document.querySelector('p');
  question.innerText = description;

  // The answers
  const answers = questionDataArr[index].answers;

  let ansArr = [];

  for (let ans in answers) {
    ansArr.push(ans);
  }
  document.querySelector('#answer1id ~ label').innerHTML = ansArr[0];
  document.querySelector('#answer2id ~ label').innerHTML = ansArr[1];
  document.querySelector('#answer3id ~ label').innerHTML = ansArr[2];
  document.querySelector('#answer4id ~ label').innerHTML = ansArr[3];
}

function analyzeTest() { // Analyze the users test
  console.log(JSON.stringify(testAnalysis))
  fetch('http://localhost:8080/testAnalysis', {
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    method: 'post',
    body: JSON.stringify(testAnalysis)
  }).then(function (response) {
    if (response.ok) {
      return response.json();
    } else {
      return Promise.reject(response);
    }
  }).then(function (responseJSON) {
    localStorage["analysisByServer"] = JSON.stringify(responseJSON);
    localStorage["userTest"] = JSON.stringify(testAnalysis);
    localStorage["fullTest"] = JSON.stringify(questionDataArr);
    if (window.confirm('Really go to another page?')) {
      window.location.href = "/analysis.html";
    }
  }).catch(function (err) {
    console.log(err);
  });
}

function clearRadioButton() {
  var ele = document.getElementsByName("groupOfMaterialRadios");
  for (var i = 0; i < ele.length; i++)
    ele[i].checked = false;
}
