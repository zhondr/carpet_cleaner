import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class IntervalService {

  intervals: Set<any> = new Set();

  constructor() {
  }

  make(callback: (...args: any[]) => void, ms: number, ...args: any[]) {
    let newInterval = setInterval(callback, ms, ...args);

    this.intervals.add(newInterval);
    return newInterval;
  }

  clear(id) {
    this.intervals.delete(id);
    return clearInterval(id);
  }

  clearAll() {
    this.intervals.forEach(value => {
      this.clear(value);
    });
  }
}
