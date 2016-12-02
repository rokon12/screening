/**
 * @author Bazlur Rahman Rokon
 * @since 12/2/16.
 */

'use strict';

const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');
const Button = require('react-bootstrap').Button;
const FontAwesome = require('react-fontawesome');

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
            <Question key={question._links.self.href} question={question}/>
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
    render() {
        return (  <tr>
            <td>{this.props.question.id}</td>
            <td>{this.props.question.difficulty}</td>
            <td>{this.props.question.maxScore}</td>
            <td>{this.props.question.allocatedTime}</td>
            <td>{this.props.question.name}</td>
            <td>
                <div className="btn-toolbar">
                    <ControlButton clazz='fa fa-edit' name='Edit'/>
                    <ControlButton clazz='fa fa-bar-chart' name='Inside'/>
                    <ControlButton clazz='fa fa-remove' name='Delete'/>
                </div>
            </td>
        </tr>)
    }
}

class ControlButton extends React.Component {
    render() {
        return (
            <a className="btn btn-default btn-xs"> <i className={this.props.clazz}></i> {this.props.name}</a>
        )
    }
}

ReactDOM.render(<App />, document.getElementById('react'));