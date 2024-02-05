import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from './article';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private http: HttpClient) { }

  getArticles(searchValue: String): Observable<Article[]> {
    return this.http.get<Article[]>(`http:localhost:8080/products?title_like=${searchValue}`)
  }
}
