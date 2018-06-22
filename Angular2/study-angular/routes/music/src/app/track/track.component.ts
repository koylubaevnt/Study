import { Component, OnInit } from '@angular/core';

import { SportifyService } from '../sportify.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-track',
  templateUrl: './track.component.html',
  styleUrls: ['./track.component.css']
})
export class TrackComponent implements OnInit {

  id:string;
  track: Object;

  constructor(private route: ActivatedRoute, private spotify: SportifyService,
    private location: Location) {
      route.params.subscribe(params => { this.id = params['id']; });
     }

  ngOnInit() {
    this.spotify
      .getTrack(this.id)
      .subscribe(res => { this.renderTrack(res) });
  }

  back(): void {
    this.location.back();
  }

  renderTrack(res: any): void {
    this.track = res;
  }
}
