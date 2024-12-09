import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';


@Component({
  selector: 'app-edit-profile',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.scss'
})
export class EditProfileComponent implements OnInit{
  profileForm: FormGroup;
  userInfo: any;

  constructor(private authService: AuthService, private router: ActivatedRoute, private fb: FormBuilder, private userService: UserService) {
    this.profileForm = this.fb.group({
      username: ['',],
      email: ['',],
      password: ['',]
    });
  }

  ngOnInit(): void {
    const userId = this.router.snapshot.paramMap.get('id');
    if(userId) {
      this.authService.getUserInfo(userId).subscribe({
        next: (data) => {
          this.userInfo = data;
          console.log('User info', this.userInfo);

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






  submitChanges() {
    if (this.profileForm.invalid) {
      return;
    }
    console.log("Form data", this.profileForm.value);
    this.userService.updateUser(this.userInfo.id,this.profileForm.value).subscribe({
      next: response => {
        console.log("Success", response);
      },
      error: error => {
        console.log("Error", error);
      },
      complete: () => {
        console.log("Update complete");
      }
    });
  }

}
