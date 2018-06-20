import {
    SaveForm
} from '../actions/facility';

export const initialState = {
    form: null,
};


export default function(state = initialState, action) {
    switch(action.type){

        case SaveForm:
            return {
                form: action.payload
            }
            
        default: 
            return state;
    }
}
