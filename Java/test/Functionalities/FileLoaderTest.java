package Tests.Functionalities;

import Model.Abstract;
import Modules.Input.FilesLoader;
import Util.Paths;
import java.util.ArrayList;
import org.junit.Test;


public class FileLoaderTest {
    
    @Test
    public void testFileLoading(){
        ArrayList<Abstract> list = FilesLoader.loadAbstracts(Paths.ABSTRACTS);
        for(Abstract t : list){
            System.out.println(t.toString());
            System.out.println("\n entrei");
        }
    }
    
    
}
