import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profile',
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit{

  userInfo: any;
  constructor(private authService: AuthService, private router: ActivatedRoute) {}

  ngOnInit(): void {
    const userId = this.router.snapshot.paramMap.get('id');
    if(userId) {
      this.authService.getUserInfo(userId).subscribe({
        next: (data) => {
          this.userInfo = data;
        },
        error: (err) => {
          console.error('Error fetching user info', err);
        }
      });
    }
    else {
      console.error('No user id provided');
    }
  }

}
