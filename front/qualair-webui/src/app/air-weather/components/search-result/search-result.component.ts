import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.scss']
})
export class SearchResultComponent {
  city!: string;

  constructor(private route: ActivatedRoute,
              private router: Router) {
    const  city = route.snapshot.queryParamMap.get('city');
    if (city == null) {
      // this.router.navigate(['air-weather', 'search']);
    } else {
      this.city = city;
    }
  }
}
