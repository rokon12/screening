/**
 * @author Bazlur Rahman Rokon
 * @since 12/2/16.
 */

'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const Button = require('react-bootstrap').Button;
const ButtonToolbar = require('react-bootstrap').ButtonToolbar;

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {questions: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/v1/questions'})
            .done(response => {
                console.log(response);
                this.setState({questions: response.entity._embedded.questions});
            });
    }

    render() {
        return (
            <QuestionList questions={this.state.questions}/>
        )
    }
}

class QuestionList extends React.Component {
    render() {
        var questions = this.props.questions.map(question =>
            <Question key={question.id} question={question}/>
        );

        return (
            <table className="table table-hover">
                <tbody>
                <tr>
                    <th>ID</th>
                    <th>Difficulty</th>
                    <th>Max Score</th>
                    <th>Allocated Time (Seconds)</th>
                    <th>Name</th>
                    <th>#</th>
                </tr>
                {questions}
                </tbody>
            </table>
        )
    }
}

class Question extends React.Component {
    constructor(props) {
        super(props);
        this.state = {question: this.props.question};
    }

    showInside = (event) => {
        console.log("id: " + this.state.question.id)
    };

    editQuestion = (event) => {
        console.log("edit: " + this.state.question.id)
    };

    deleteQuestion = (event) => {
        console.log("delete: " + this.state.question.id)
    };

    render() {
        return (  <tr>
            <td>{this.state.question.id}</td>
            <td>{this.state.question.difficulty}</td>
            <td>{this.state.question.maxScore}</td>
            <td>{this.state.question.allocatedTime}</td>
            <td>{this.state.question.name}</td>

            <td>
                <ButtonToolbar>
                    <Button bsSize="xs" href="#" onClick={this.editQuestion}> <i className="fa fa-edit"></i> Edit</Button>
                    <Button bsSize="xs" href="#" onClick={this.showInside}> <i className="fa fa-bar-chart"></i> Inside</Button>
                    <Button bsSize="xs" href="#" onClick={this.deleteQuestion}> <i className="fa fa-remove"></i> Delete</Button>
                </ButtonToolbar>
            </td>
        </tr>)
    }
}

ReactDOM.render(<App />, document.getElementById('react'));