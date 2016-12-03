/**
    * @author Bazlur Rahman Rokon
    * @since 12/4/16.
    */

const React = require('react');
const Question = require('./Question');

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

module.exports = QuestionList;