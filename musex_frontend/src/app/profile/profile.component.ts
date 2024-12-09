import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';
import { RouterLink, RouterLinkActive } from '@angular/router';


@Component({
  selector: 'app-profile',
  imports: [CommonModule,RouterLink,RouterLinkActive],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit{

  userInfo: any;
  isOwnProfile: boolean = false;
  constructor(private authService: AuthService, private router: ActivatedRoute) {}

  ngOnInit(): void {
    const userId = this.router.snapshot.paramMap.get('id');
    if(userId) {
      this.authService.getUserInfo(userId).subscribe({
        next: (data) => {
          this.userInfo = data;
          console.log('User info', this.userInfo);
          this.isOwnProfile = this.authService.getUserId() == userId;
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
