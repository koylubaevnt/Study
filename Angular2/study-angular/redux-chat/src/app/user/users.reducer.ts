import { User } from '../user/user.model';
import { createSelector } from 'reselect';
import { Action } from 'redux';
import { AppState } from '../app.reducer';
import * as UserActions from '../user/user.actions';

export interface UsersState {
    currentUser: User;
}

const initialState: UsersState = {
    currentUser: null
};

export const UsersReducer =
(state: UsersState = initialState, action: Action) => {
    switch(action.type) {
        case UserActions.SET_CURRENT_USER:
            const user: User = (<UserActions.SetCurrentUserAction> action).user;
            return {
                currentUser: user
            };
        default:
            return state;
    }
}

export const getUsersState = (state: AppState): UsersState => state.users;

export const getCurrentUser = createSelector(
    getUsersState,
    (state: UsersState) => state.currentUser
);
