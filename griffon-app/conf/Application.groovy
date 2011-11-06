application {
    title = 'BarusuCounter'
    startupGroups = ['barusuCounter']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "barusuCounter"
    'barusuCounter' {
        model      = 'barusucounter.BarusuCounterModel'
        view       = 'barusucounter.BarusuCounterView'
        controller = 'barusucounter.BarusuCounterController'
    }

}
