import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private termSearch = new BehaviorSubject<string>("")

  search$ = this.termSearch.asObservable();
  
  setSeachBiahvior(term: string):void{
    this.termSearch.next(term);
  }
}
