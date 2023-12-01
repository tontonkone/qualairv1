import { Component } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.scss']
})
export class SearchFormComponent {
  searchForm = this.fb.group({
    city: ['', Validators.required]
  });
  constructor(private fb: FormBuilder,
              private router: Router) {
  }

  onSubmit() {
    let city = this.searchForm.getRawValue().city;
    if (city != null) {
      this.router.navigate(['air-weather', 'result'], {
        queryParams: {city}
      });
    }
  }
}
