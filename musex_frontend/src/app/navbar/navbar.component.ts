import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule} from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-navbar',
  imports: [RouterLink,RouterLinkActive,CommonModule,ReactiveFormsModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent {
  userId: string | null;
  searchForm: FormGroup;
  constructor(public authService: AuthService, private router: Router, private fb: FormBuilder) {


    this.userId = this.authService.getUserId();
    this.searchForm = this.fb.group({
      search: ['']
    });


  }


  onSubmit() {
    if (this.searchForm.invalid) {
      return;
    }
    this.router.navigate(['/search'], { queryParams: { q: this.searchForm.value.search } });
  }


}
