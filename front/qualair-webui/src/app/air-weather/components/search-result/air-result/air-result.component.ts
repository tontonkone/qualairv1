import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {AirQuality} from "../../../../shared/models/air-quality";
import {AirQualityService} from "../../../../shared/services/air-quality.service";

@Component({
  selector: 'app-air-result',
  templateUrl: './air-result.component.html',
  styleUrls: ['./air-result.component.scss']
})
export class AirResultComponent implements OnInit {
  @Input() city!: string;
  airQuality!: Observable<AirQuality>;

  constructor(private airQualityService: AirQualityService) {
  }

  ngOnInit(): void {
    if (this.city) {
      this.airQuality = this.airQualityService.findByCityName(this.city);
    }
  }
}
