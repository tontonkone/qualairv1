import { Component } from '@angular/core';
import { SearchService } from 'src/app/shared/services/search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent {

  searchTerm!: string;

  constructor(
    private _searchService: SearchService,){

  }

  onSearchChange() :void{
    this._searchService.setSeachBiahvior(this.searchTerm)
  }

}
