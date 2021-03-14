package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.login.LoginComponent
import com.example.android.dagger.registration.RegistrationComponent
import com.example.android.dagger.user.UserComponent
import com.example.android.dagger.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/** If we annotate a Component with @Singleton,
 * all the classes also annotated with @Singleton will be scoped to its lifetime. */
@Singleton

// Definition of a dagger component(s)
@Component(modules = [StorageModule::class, AppSubComponents::class])
interface AppComponent {


    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {

        // with @BindsInstance, the Context passed in will be available in the graph!
        fun create(@BindsInstance context: Context): AppComponent

        // @BindsInstance tells Dagger that it needs to add that instance
        // in the graph and whenever Context is required, provide that instance.
    }


    // Expose RegistrationComponent factory from the graph
    fun registrationComponent(): RegistrationComponent.Factory

    // Expose LoginComponent factory from the graph
    fun loginComponent(): LoginComponent.Factory

    // Expose UserManager so that MainActivity and SettingsActivity
    // can access a particular instance of UserComponent
    fun userManager(): UserManager


}


//    A @Component interface gives the information Dagger needs to generate the graph at compile-time.
//    The parameter of the interface methods define what classes request injection.
