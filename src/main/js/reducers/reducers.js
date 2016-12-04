/**
 * @author Bazlur Rahman Rokon
 * @since 12/5/16.
 */

const mainReducer = (state = {}, action) => {

    switch (action.type) {
        case 'RECEIVE_QUESTION':
            return {
                ...state,
                questions: action.questions
            };

        default:
            return state;
    }
};

export default mainReducer;