import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AlumnoService, Alumno } from '../../service/alumno.service';

@Component({ selector: 'app-alumnos', standalone: true, imports: [CommonModule, ReactiveFormsModule], templateUrl: './alumnos.component.html', styleUrl: './alumnos.component.css' })
export class AlumnosComponent implements OnInit {
  alumnos: Alumno[] = [];
  form: FormGroup;
  mensaje = '';
  constructor(private fb: FormBuilder, private servicio: AlumnoService) {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      dni: ['', [Validators.required, Validators.pattern(/^\d{7,8}$/)]],
      email: ['', [Validators.required, Validators.email]]
    });
  }
  ngOnInit(): void { this.cargar(); }
  invalido(campo: string): boolean {
    const c = this.form.get(campo);
    return !!c && c.invalid && c.touched;
  }
  cargar(): void {
    this.servicio.listarAlumnos().subscribe({ next: d => this.alumnos = d, error: () => this.mensaje = 'No se pudo cargar la lista' });
  }
  guardar(): void {
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }
    this.servicio.agregarAlumno(this.form.value).subscribe({
      next: () => { this.form.reset(); this.mensaje = ''; this.cargar(); },
      error: () => this.mensaje = 'No se pudo guardar'
    });
  }
}