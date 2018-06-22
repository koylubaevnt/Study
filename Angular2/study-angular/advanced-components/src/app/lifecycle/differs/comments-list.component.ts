import { Component, OnInit, DoCheck, IterableDiffers } from '@angular/core';

@Component({
  selector: 'app-comments-list',
  templateUrl: './comments-list.component.html',
  styleUrls: []
})
export class CommentsListComponent implements OnInit, DoCheck {

  comments: any[];
  iterable: boolean;
  authors: string[];
  texts: string[];
  differs: any;
  
  constructor(differs: IterableDiffers) {
    this.differs = differs.find([]).create(null);
    this.comments = [];

    this.authors = ['Elliot', 'Helen', 'Jenny', 'Joe', 'Justen', 'Matt'];
    this.texts = [
      "Ours is a life of constant reruns. We're always circling back to where we'd we started, then starting all over again. Even if we don't run extra laps that day, we surely will come back for more of the same another day soon.",      
      'Really cool!',
      'Thanks!'
    ];

    this.addComment();
  }

  addComment(): void {
    this.comments.push({
      author: this.getRandomItem(this.authors),
      comment: this.getRandomItem(this.texts),
      likes: this.getRandomInt(20)
    });
  }
  
  removeComment(comment): void {
    const pos: number = this.comments.indexOf(comment);
    this.comments.splice(pos, 1);
  }

  getRandomInt(max: number): number {
    return Math.floor(Math.random() * (max + 1));
  }

  getRandomItem(array: string[]): string {
    const pos: number = this.getRandomInt(array.length - 1);
    return array[pos];
  }

  ngOnInit(): void {
  }

  ngDoCheck(): void {
    const changes = this.differs.diff(this.comments);

    if(changes) {
      changes.forEachAddedItem(i => console.log('Added', i.item));
      changes.forEachRemovedItem(i => console.log('Removed', i.item));
    }
  }
}
