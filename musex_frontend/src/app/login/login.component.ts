import { AuthService } from './../services/auth.service';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

    lofinForm: FormGroup;
    constructor(private formBuilder: FormBuilder, private authService: AuthService, private router: Router){
      this.lofinForm = this.formBuilder.group({
        username: ['',[Validators.required]],
        password: ['', [Validators.required]]
      })
    }

    onSubmit(){
      if(this.lofinForm.invalid){
        return;
      }
      this.authService.logIn(this.lofinForm.value).subscribe({
        next: response => {
          console.log("Success", response);
          this.router.navigate(['/']);
        },
        error: error => {
          console.log("Error", error);
        },
        complete: () => {
          console.log("Login complete");
        }
      });
    }
}
