/**
 * @author Bazlur Rahman Rokon
 * @since 12/5/16.
 */

import  client from  '../client';
import when from 'when';

export const receiveQuestion = (questions) =>({
    type: 'RECEIVE_QUESTION',
    questions
});

export const fetchQuestion = () => {
    return when.promise(client({method: 'GET', path: '/api/v1/questions'}))
        .then(response => response.entity._embedded.questions)
        .then(questions => {
            return receiveQuestion(questions)
        });
};
