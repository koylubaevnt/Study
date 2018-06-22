export class Counter {
  id: string;
  max: number;
  current: number;
  status: Status;
}

export enum Status {
  RUNNING,
  PAUSED,
  STOPPED
}
