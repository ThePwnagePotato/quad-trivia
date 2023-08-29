import React, {Component} from 'react';
import {decode} from 'html-entities';

const getURL = 'http://localhost:8080/questions';
const postURL = 'http://localhost:8080/checkanswers';

let questions = [];
let counter = 0;

// Returns a jsx element containing buttons for all answers to a question, and the check answers button
// Handles answer selection and answer checking
class AnswerRadioButtons extends Component {
    constructor(props) {
        super(props);
        this.state = {
            checked: {},
            selected: -1
        }
    }

    // Checks if currently selected answer is correct and stores it in state, then updates the state
    checkAnswer = () => {
        // Prevent duplicate requests
        let selected = this.state.selected;
        if (selected < 0 || this.state.checked.hasOwnProperty(selected)) {
            return;
        }
        let payload = {
            question: this.props.question.question,
            answer: this.props.question.answers[this.state.selected]
        }
        fetch(postURL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
            .then(res => res.json())
            .then(data => {
                // Set selected to either true or false
                this.setState(state => state.checked[selected] = data.correct)
            })
            .catch(e => console.log(e))
    }

    // Handles answer selection
    handleSelectChange = (event) => {
        this.setState({selected: event.target.id});
    }

    // Shows currently selected button or if an answer correct
    getStyle = (id) => {
        if (this.state.checked.hasOwnProperty(id)) {
            // Correct answer
            if (this.state.checked[id]) {
                return {backgroundColor: 'green'}
            } else { // Incorrect
                return {backgroundColor: 'red'}
            }
        }
        // If selected or not
        return {backgroundColor: this.state.selected == id ? 'grey' : 'white'}
    }

    render() {
        let elements = [];
        for (let i = 0; i < this.props.question.answers.length; i++) {
            let ans = this.props.question.answers[i];
            elements.push(
                <input
                    id={i}
                    key={i}
                    type="button"
                    value={decode(ans)}
                    style={this.getStyle(i)}
                    onClick={this.handleSelectChange.bind(this)}
                />
            )
        }
        return (
            <div>
                {elements}
                {' '}
                <button
                    onClick={this.checkAnswer.bind(this)}>
                    Check answer
                </button>
            </div>
        );
    }
}

// Displays the question and info on the question
function displayQuestion(question) {
    return <div key={question.question}>
        <hr/>
        <h6>Category : {question.category} &nbsp; Difficulty : {question.difficulty}</h6>
        <h4>{decode(question.question)}</h4>
        <AnswerRadioButtons question={question} key={counter++}/>
    </div>
}

// Displays all questions
function displayQuestions(questions) {
    let elements = [];
    for (let i = 0; i < questions.length; i++) {
        const q = questions[i];
        elements.push(displayQuestion(q, i));
    }
    return (
        <div>
            {elements}
        </div>
    );
}

// Displays the
class App extends Component {
    constructor(props) {
        super(props);
        this.state = {questions: []};
    }

    // Gets questions from backend and stores them in state
    getQuestions = () => {
        fetch(getURL)
            .then(res => res.json())
            .then(data => {
                questions = [];
                data.forEach(e => questions.push(e));
                this.setState({questions: questions});
            })
            .catch(e => console.log(e))
    }

    render() {
        return (
            <div className="App">
                <h1>
                    Free trivia
                </h1>
                <button onClick={this.getQuestions}>
                    Get new questions
                </button>
                {this.state.questions.length ? displayQuestions(this.state.questions) : ""}
            </div>
        )
    }
}

export default App;
