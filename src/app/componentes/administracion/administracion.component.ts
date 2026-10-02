import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AdministracionService, Personal } from '../../service/administracion.service';

@Component({ selector: 'app-administracion', standalone: true, imports: [CommonModule, ReactiveFormsModule], templateUrl: './administracion.component.html', styleUrl: './administracion.component.css' })
export class AdministracionComponent implements OnInit {
  personal: Personal[] = [];
  form: FormGroup;
  mensaje = '';
  constructor(private fb: FormBuilder, private servicio: AdministracionService) {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cargo: ['', Validators.required]
    });
  }
  ngOnInit(): void { this.cargar(); }
  invalido(campo: string): boolean {
    const c = this.form.get(campo);
    return !!c && c.invalid && c.touched;
  }
  cargar(): void {
    this.servicio.listarPersonal().subscribe({ next: d => this.personal = d, error: () => this.mensaje = 'No se pudo cargar la lista' });
  }
  guardar(): void {
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }
    this.servicio.agregarPersonal(this.form.value).subscribe({
      next: () => { this.form.reset(); this.mensaje = ''; this.cargar(); },
      error: () => this.mensaje = 'No se pudo guardar'
    });
  }
}