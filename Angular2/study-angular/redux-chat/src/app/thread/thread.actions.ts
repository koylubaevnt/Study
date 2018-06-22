import { Action, ActionCreator } from 'redux';
import { Thread } from './thread.model';
import { Message } from '../message/message.model';
import { uuid } from '../util/uuid';

//Action Add Thread
export const ADD_THREAD = '[Thread] Add';
export interface AddThreadAction extends Action {
    thread: Thread
};
export const addThread : ActionCreator<AddThreadAction> = 
    (thread: Thread): AddThreadAction => ({
        type: ADD_THREAD,
        thread: thread
    });

//Action Add Message
export const ADD_MESSAGE = '[Thread] Add Message';
export interface AddMessageAction extends Action {
    thread: Thread,
    message: Message;
};
export const addMessage: ActionCreator<AddMessageAction> =
    (thread: Thread, messageIn: Message): AddMessageAction => {
        const defaults = {
            id: uuid(),
            sentAt: new Date(),
            isRead: false,
            thread: thread
        };

        const message: Message = Object.assign({}, defaults, messageIn);

        return {
            type: ADD_MESSAGE,
            thread: thread,
            message: message
        };
    };

//Action Select Thread
export const SELECT_THREAD = "[Thread] Select Thread";
export interface SelectThreadAction extends Action {
    thread: Thread
};
export const selectThread: ActionCreator<SelectThreadAction> =
    (thread: Thread) => ({
        type: SELECT_THREAD,
        thread: thread
    });
