interface Action {
    type: string;
    payload?: any;
}

interface Reducer<T> {
    (state: T, action: Action): T;
}

let incrementAction: Action = { type: 'INCREMENT' };
let decrementAction: Action = { type: 'DECREMENT' };

let reducer: Reducer<number> = (state: number, action: Action) => {
    switch(action.type) {
        case 'INCREMENT':
            return state + 1;
    
        case 'DECREMENT':
            return state - 1;
        default: 
            return state;
    }
}

console.log(reducer(0, incrementAction ));
console.log(reducer(1, incrementAction ));
console.log(reducer(100, decrementAction ));

let unknownAction: Action = { type: 'UNKNOWN' };
console.log(reducer(100, unknownAction)); // -> 100