
import { Action } from 'redux';
import { createSelector } from 'reselect';

import { AppState } from '../app.reducer';
import { Thread } from '../thread/thread.model';
import { Message } from '../message/message.model';
import * as ThreadActions from './thread.actions';

export interface ThreadsEntities {
    [id: string]: Thread;
};

export interface ThreadsState {
    ids: string[];
    entities: ThreadsEntities;
    currentThreadId?: string;
};

const initialState: ThreadsState = {
    ids: [],
    entities: {},
    currentThreadId: null
};

export const ThreadsReducer =
    (state: ThreadsState = initialState, action: Action) => {
        switch(action.type) {
            case ThreadActions.ADD_THREAD: {
                const thread: Thread = (<ThreadActions.AddThreadAction>action).thread;

                if(state.ids.includes(thread.id)) {
                    return state;
                }

                return {
                    ids: [...state.ids, thread.id],
                    currentThreadId: state.currentThreadId,
                    entities: Object.assign({}, state.entities, {
                        [thread.id]: thread
                    })
                };
            }
            case ThreadActions.ADD_MESSAGE: {
                const thread: Thread = (<ThreadActions.AddMessageAction>action).thread;
                const message: Message = (<ThreadActions.AddMessageAction>action).message;

                const isRead = message.thread.id === state.currentThreadId ? true : message.isRead;

                const newMessage = Object.assign({}, message, { isRead: isRead });

                const oldThread = state.entities[thread.id];

                const newThread = Object.assign({}, oldThread, {
                    messages: [...oldThread.messages, newMessage]
                });

                return {
                    ids: state.ids,
                    currentThreadId: state.currentThreadId,
                    entities: Object.assign({}, state.entities, {
                        [thread.id]: newThread
                    })
                };
            }
            case ThreadActions.SELECT_THREAD: {
                const thread: Thread = (<ThreadActions.SelectThreadAction>action).thread;
                const oldThread: Thread = state.entities[thread.id];

                const newMessages: Message[] = oldThread.messages.map(
                    (message) => Object.assign({}, message, { isRead: true })
                );

                const newThread:Thread = Object.assign({}, oldThread, {
                    messages: newMessages
                });

                return {
                    ids: state.ids,
                    currentThreadId: thread.id,
                    entities: Object.assign({}, state.entities, {
                        [thread.id]: newThread
                    })
                };
            }
            default:
                return state;
        }
    };


    export const getThreadState = (state: AppState): ThreadsState => state.threads;

    // const getCurrentThread = (state: AppState): Thread => {
    //     let currentThreadId = state.threads.currentThreadId;
    //     return state.threads.entities[currentThreadId];
    // }

    export const getThreadsEntities = createSelector(
        getThreadState,
        (state: ThreadsState) => state.entities
    );

    export const getCurrentThread = createSelector(
        getThreadsEntities,
        getThreadState,
        (entities: ThreadsEntities, state: ThreadsState) => entities[state.currentThreadId]
    );

    export const getAllThreads = createSelector(
        getThreadsEntities,
        (entities: ThreadsEntities) => Object.keys(entities)
            .map((threadId) => entities[threadId])
    );            

    export const getUnreadMessagesCount = createSelector(
        getAllThreads,
        (threads: Thread[]) => threads.reduce(
            (unreadCount: number, thread: Thread) => {
                thread.messages.forEach((message: Message) => {
                    if(!message.isRead) {
                        ++unreadCount;
                    }
                });
                return unreadCount;
            }, 0));


    export const getAllMessages = createSelector(
        getAllThreads,
        (threads: Thread[]) => 
            threads.reduce(
            (messages, thread) => [...messages, ...thread.messages ], [])
            .sort((m1, m2) => m1.sentAt - m2.sentAt));
    
