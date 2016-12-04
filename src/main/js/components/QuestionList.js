/**
 * @author Bazlur Rahman Rokon
 * @since 12/4/16.
 */

import React from 'react';
import Question from './Question';
import configureStore from '../stores/store'
import * as actions from '../actions/actions'

class QuestionList extends React.Component {
    constructor(props) {
        super(props);
        this.store = configureStore();
        this.state = this.store.getState();
    }

    componentDidMount() {
        this.unsubscribe = this.store.subscribe(() => {
            this.setState(this.store.getState());
        });

        this.store.dispatch(actions.fetchQuestion());
    }

    componentWillUnmount() {
        this.subscribe();
    }

    deleteQuestion = (id)=> {
        // var currentQuestions = this.state.questions;
        // var questions = currentQuestions.filter(question => question.id !== id);
        // this.setState({questions: questions});
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

export default QuestionList;