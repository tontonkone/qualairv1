import { Component } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-history-search-form',
  templateUrl: './history-search-form.component.html',
  styleUrls: ['./history-search-form.component.scss']
})
export class HistorySearchFormComponent {
  searchForm = this.fb.group({
    city: ['', Validators.required],
    startDate: [{}],
    endDate: [{}]
  });
  constructor(private fb: FormBuilder,
              private router: Router) {
  }

  onSubmit() {
    let city = this.searchForm.getRawValue().city;
    if (city != null) {
      this.router.navigate(['air-weather', 'history-result'], {
        queryParams: {city}
      });
    }
  }
}
