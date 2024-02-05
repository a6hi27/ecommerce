import { Component, OnInit } from '@angular/core';
import { ArticleService } from 'src/app/services/article.service';
import { Article } from 'src/app/services/article';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'eca-searchbar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css']
})
export class SearchbarComponent implements OnInit {
  searchValue = ''
  articles: Article[] = []
  searchForm = this.fb.nonNullable.group({
    searchValue: ''
  })
  constructor(private articleService: ArticleService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(): void {
    this.articleService.getArticles(this.searchValue).subscribe((articles) => {
      this.articles = articles
    })
  }

  onSearchSubmit(): void {
    this.searchValue = this.searchForm.value.searchValue ?? '';
    this.fetchData();
  }
}
