import { AuthService } from './../services/auth.service';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators,ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

    registerForm: FormGroup;
    constructor(private formBuilder: FormBuilder,private authService:AuthService, private router: Router) {
        this.registerForm = this.formBuilder.group({
            username: ['',[Validators.required]],
            email: ['',[Validators.required, Validators.email]],
            password: ['', [Validators.required]]
        });
     }

     onSubmit() {
      if (this.registerForm.invalid) {
        return;
      }
      this.authService.register(this.registerForm.value).subscribe({
        next: response => {
          console.log("Success", response);
          this.router.navigate(['/']);
        },
        error: error => {
          console.log("Error", error);
        },
        complete: () => {
          console.log("Registration complete");

        }
      });
    }
}
