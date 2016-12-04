/**
 * @author Bazlur Rahman Rokon
 * @since 12/4/16.
 */

const React = require('react');
const Question = require('./Question');

class QuestionList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {questions: []};
    }

    componentWillReceiveProps(newProps) {
        this.setState({questions: newProps.questions});
    }

    deleteQuestion = (id)=> {
        var currentQuestions = this.state.questions;
        var questions = currentQuestions.filter(question => question.id !== id);
        this.setState({questions: questions});
    };

    render() {
        var questions = this.state.questions.map(question =>
            <Question key={question.id} question={question} handleDelete={this.deleteQuestion}/>
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

module.exports = QuestionList;