import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-history-result',
  templateUrl: './history-result.component.html',
  styleUrls: ['./history-result.component.scss']
})
export class HistoryResultComponent {
  city!: string;

  constructor(private route: ActivatedRoute,
              private router: Router) {
    let city = route.snapshot.queryParamMap.get('city');
    if (city == null) {
      // this.router.navigate(['air-weather', 'search']);
    } else {
      this.city = city;
    }
  }
}
