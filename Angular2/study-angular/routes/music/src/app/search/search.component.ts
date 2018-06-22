import { Component, OnInit } from '@angular/core';

import { SportifyService } from '../sportify.service';
import { Router, ActivatedRoute } from '@angular/router'

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  query: string;
  results: Object;

  constructor(private spotify: SportifyService,
              private router: Router,
              private route: ActivatedRoute) { 
    this.route
        .queryParams
        .subscribe(params => { this.query = params['query'] || ''; });
  }

  ngOnInit() {
    this.search();
  }

  search(): void {
    console.log('this.query', this.query);
    if(!this.query) {
      return;
    }
    console.log('search start')
    this.spotify
      .searchTrack(this.query)
      .subscribe(
        (res: any) => this.renderResults(res),
        (err: any) => console.log('Error:', err),
        () => console.log('complete')
      );
      console.log('search end')
  }

  renderResults(res: any): void {
    this.results = null;
    if(res && res.tracks && res.tracks.items) {
      this.results = res.tracks.items;
    }
  }

  submit(query: string): void {
    this.router.navigate(['search'], { queryParams: { query: query } })
      .then(_ => this.search());
  }
}
